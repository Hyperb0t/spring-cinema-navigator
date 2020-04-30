document.addEventListener("DOMContentLoaded", addListeners);
var seancePageNumber = 1;
var loading = false;
function addListeners() {
    // $(window).on('scroll', checkScroll);
    window.addEventListener("scroll", checkScroll);
}

function checkScroll() {
    console.log("scrolling");
    console.log(window.innerHeight + window.scrollY);
    console.log(document.body.offsetHeight-100);
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight-100) {
        console.log("loading seances");
        loadSeances();
    }
}

function loadSeances() {
    if(!loading) {
        console.log("started loading");
        loading = true;
        $.ajax({
            url: "/search/rest/all",
            data: {"page": seancePageNumber},
            success: function (result) {
                seancePageNumber += 1;
                console.log("adding to DOM")
                addSeancesToDOM(result);
            }
        });
    }
}

function addSeancesToDOM(seances) {
    for(let i = 0; i < seances.length; i++) {
        let startTime = new Date(1000*seances[i]['startTime']);
        startTime = startTime.getDate() +"." + (startTime.getMonth()+1) + "." + (startTime.getFullYear() %100) + " " +
            startTime.getHours() + ":" + startTime.getMinutes();
        let endTime = new Date(1000*seances[i]['endTime']);
        endTime = endTime.getDate() +"." + (endTime.getMonth()+1) + "." + (endTime.getFullYear() %100) + " " +
            endTime.getHours() + ":" + endTime.getMinutes();
        console.log("cycle iteration " + i);
        seances[i] = `<div class="search-result my-4 col-lg-11 col-md-11 col-sm-11 mx-auto d-flex bg-light border flex-lg-row flex-column
                justify-content-center">
        <div class="img col-lg-3 col-md-10 col-sm-10 my-auto mx-auto d-flex justify-content-center">
            <img src="${seances[i]['film']['imageLink']}" class="rounded my-2 w-75" alt="Picture">
        </div>
        <div class="text-column col-lg-3 col-md-10 col-sm-10 my-auto mx-auto">
            <h5>
                <a href="/film?id=${seances[i]['film']['id']}">${seances[i]['film']['title']}</a>
            </h5>
            <p>${startTime} - ${endTime}
            </p>
            <p>${seances[i]['price']}₽</p>
        </div>
        <div class="text-column col-lg-3 col-md-10 col-sm-10 my-auto mx-auto">
            <p>${seances[i]['film']['description']}</p>
        </div>
        <div class="col-lg-3 col-md-10 col-sm-10 d-flex justify-content-center mx-auto">
            <form class="col-9 my-auto" action="/purchase">
                <input type="hidden" value="${seances[i]['id']}" name="seanceId">
                <input type="submit" class="btn btn-primary col-12 my-auto" value="Купить билет">
            </form>
        </div>
    </div>`;
    }
    document.getElementById("seancelist").innerHTML =
        document.getElementById("seancelist").innerHTML + seances.join("\n");
    loading = false;
    console.log("finished loading and added to DOM");
}

