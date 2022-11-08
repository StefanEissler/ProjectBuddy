let forecastsum;
let spendingsum;
let budget = 2000;

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

    const { title, date, value, category } = object

    const costContainerName = document.createElement("div")
    costContainerName.className="cost-container-name"
    const costContainer = document.createElement("div")
    costContainer.className = "cost-container"

    createTextElementNode("h3", title, costContainer, "title-text")
    costContainer.appendChild(costContainerName)
    createTextElementNode("p","Höhe",costContainerName,"hoehe")
    createTextElementNode("p","Datum",costContainerName,"datum")
    createTextElementNode("p","Kategorie",costContainerName,"kategorie")
    createTextElementNode("p", value+"€", costContainer, "value-text")
    createTextElementNode("p", date, costContainer, "date-text")
    createTextElementNode("p", category, costContainer, "category-text")
    createElementNode("i",costContainer, "fa-solid fa-pen",updatedEntry)
    createElementNode("i",costContainer, "fa-solid fa-repeat",switchEntry)
    createElementNode("i",costContainer, "fa-solid fa-trash-can",deleteEntry)
    container.appendChild(costContainer);

}

const updatedEntry=() => {
    console.log("UPDATE")
    //Request UPDATE
}

const switchEntry=() => {
    console.log("SWITCH")
    //Request SWITCH
}
const deleteEntry=() => {
    console.log("DELETE")
    //Request DELETE
}

const FALLSTUDIE_HEADERS = {
    'Content-Type': 'application/json'
}

const loadData = async () => {
    // Fetching vom JSON
    const spending_response = await fetch('http://localhost:8080/api/booking', {
        headers: FALLSTUDIE_HEADERS
    });

    const spending_array = await spending_response.json();

    const forecast_response = await fetch('./forecast.json');
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

    document.getElementById("chart-spending-value").innerHTML=spendingsum+'€';
    document.getElementById("chart-forecast-value").innerHTML=forecastsum+'€';
    document.getElementById("chart-budget-value").innerHTML=budget+'€';

    function getsinglevalues(item) {
        return item.value;
    }
    function getSum(total, num) {
        return total + Math.round(num);
    }

    // Anteil in % Ausrechnen dabei Budget komplett = 100%
    let chartSpendingsum = (spendingsum/budget)*100;
    let chartForecastsum = (forecastsum/budget)*100 ;
    let chartbudget = ((budget - (forecastsum+spendingsum))/budget)*100;

    console.log(chartSpendingsum);

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
