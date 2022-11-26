let forecastsum;
let spendingsum;

const params = new Proxy(new URLSearchParams(window.location.search), {
    get: (searchParams, prop) => searchParams.get(prop),
});

let budgetId = parseInt(params.budget);
let budget = 0;

//Catching des orginalen Container
const spending_container = document.getElementById("list-spending"); // Reference div id=container
const forecast_container = document.getElementById("list-forecast");


//Erstellen der Text Nodes im Costcontainer
const createTextElementNode = (datatype, text, container, className) => {
    const tag = document.createElement(datatype)
    const textNode = document.createTextNode(text)
    tag.className = className ? className : ""
    tag.appendChild(textNode)
    container.appendChild(tag)
}

//Erstellen der icons im Costcontainer
const createElementNode = (datatype, container, className, onClick) => {
    const tag = document.createElement(datatype)
    tag.addEventListener("click",onClick);
    tag.className = className ? className : ""
    container.appendChild(tag)
}


//Erstellen der Nodes für jedes JSON
const createCostContainer = (object, container) => {

    const { id, title, timestamp, amount, category } = object

    const costContainer = document.createElement("div")
    costContainer.className = "cost-container"
    const costContainerSub = document.createElement("div")
    costContainerSub.className = "cost-container-sub"
    const costContainerIcon = document.createElement("div")
    costContainerIcon.className = "cost-container-icon"
    const costContainerAmount = document.createElement("div")
    costContainerAmount.className = "cost-container-amount"
    const costContainerDate = document.createElement("div")
    costContainerDate.className = "cost-container-date"
    const costContainerCategory = document.createElement("div")
    costContainerCategory.className = "cost-container-category"

    createTextElementNode("h3", title, costContainer, "title-text")
    costContainer.appendChild(costContainerSub)
    costContainer.appendChild(costContainerIcon)
    costContainerSub.appendChild(costContainerAmount)
    costContainerSub.appendChild(costContainerDate)
    costContainerSub.appendChild(costContainerCategory)


    createTextElementNode("h4","Höhe",costContainerAmount,"hoehe")
    createTextElementNode("p", amount+"€", costContainerAmount, "value-text")

    createTextElementNode("h4","Datum",costContainerDate,"datum")
    createTextElementNode("p", timestamp, costContainerDate, "date-text")

    createTextElementNode("h4","Kategorie",costContainerCategory,"kategorie")
    createTextElementNode("p", category, costContainerCategory, "category-text")


    createElementNode("i",costContainerIcon, "fa-solid fa-pen",()=>updatedEntry(object))
    createElementNode("i",costContainerIcon, "fa-solid fa-repeat",()=>switchEntry(object))
    createElementNode("i",costContainerIcon, "fa-solid fa-trash-can",()=>deleteEntry(id))
    container.appendChild(costContainer);
}

//Modal öffnen
const updatedEntry=(opened) => {
    console.log("UPDATE")
    const modal=document.getElementById("modal");
    openedBooking = opened;
    modal.showModal();
}

//Request SWITCH
const switchEntry=(opened) => {
    console.log("SWITCH")
    fetch("http://localhost:8080/api/booking/"+opened.id, {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            title: opened.title,
            amount: opened.amount,
            category: opened.category === "Forecast" ? "Cost": "Forecast"
        })
    }).then(response => response.json())
        .then(data => console.log(data))
        .finally(() => location.reload())
}
//Request DELETE
const deleteEntry=(id) => {
    fetch("http://localhost:8080/api/booking/"+id,
        {method: "DELETE"})
        .then(response => response.json())
        .then(data => {console.log(data);})
        .finally( () => location.reload())
    console.log("DELETE")
}

const addEntry=(event) => {
    fetch("http://localhost:8080/api/booking/add", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            title: event.target[0].value,
            amount: event.target[1].value,
            category: event.target[2].value
        })
    }).then(response => response.json())
        .then(data => console.log(data))
        .finally(() => location.reload())
}

const loadData = async () => {
    // Fetching vom JSON
    const spending_response = await fetch('http://localhost:8080/api/booking/costs/'+budgetId)
    const spending_array = await spending_response.json();

    const forecast_response = await fetch('http://localhost:8080/api/booking/forecasts/'+budgetId);
    const forecast_array = await forecast_response.json();

    // Creating von den einzelnen Divs für die Cost-/Forecast-List
    spending_array.map((item) => {
        createCostContainer(item, spending_container)
    })
    forecast_array.map((item) => {
        createCostContainer(item, forecast_container)
    })



    //Forecast und Kosten insgesamt herrausfinden
    const forecastvalues = forecast_array.map(getsinglevalues);
    const spendingvalues = spending_array.map(getsinglevalues);

    forecastsum = forecastvalues.reduce(getSum, 0);
    spendingsum = spendingvalues.reduce(getSum, 0);
    console.log(spendingsum);
    document.getElementById("chart-spending-value").innerHTML=spendingsum+'€';
    document.getElementById("chart-forecast-value").innerHTML=forecastsum+'€';
    document.getElementById("chart-budget-value").innerHTML=budget+'€';

    function getsinglevalues(item) {
        return item.amount;
    }

    function getSum(total, num) {
        return total + Math.round(num);
    }

    // Anteil in % Ausrechnen dabei Budget komplett = 100%
    let chartSpendingsum = (spendingsum/budget)*100;
    let chartForecastsum = (forecastsum/budget)*100 ;
    let chartbudget = ((budget - (forecastsum+spendingsum))/budget)*100;


    //Setzen der divs auf den prozentualen Anteil
    const cs = document.querySelector('.chart-spending');
    cs.style.height=chartSpendingsum +"%";

    const cf = document.querySelector('.chart-forecast');
    cf.style.height=chartForecastsum+"%";

    const cb = document.querySelector('.chart-budget');
    cb.style.height=chartbudget+"%";
}


loadData().then((message) => { // We don't return something
        console.log('This is a' + message) // Promises did not fail
    }).catch((message) => {
        console.log('This is a' + message) // Promises did fail
    }
)

//Switch zwischen Cost und Forecast
function showforecast() {
    document.getElementById('list-forecast').style.display = "block";
    document.getElementById('list-spending').style.display = "";
}
function showcost() {
    document.getElementById('list-spending').style.display = "block";
    document.getElementById('list-forecast').style.display = "";
}
// Das Cost zuerst angezeigt wird
showcost();
