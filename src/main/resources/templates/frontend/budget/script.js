const BudgetContainer = document.getElementById("budget-bubbles"); // Reference div id=container


const createNode = (datatype, text, container, className) => {
    const tag = document.createElement(datatype) // Parent
    const textNode = document.createTextNode(text) // Child
    tag.className = className ? className : ""
    tag.appendChild(textNode)
    container.appendChild(tag)
}

const createBubbleContainer = (object, container) => {

    const { name, date, amount } = object

    const BubbleContainer = document.createElement("div")
    BubbleContainer.className = "bubble-container"

    createNode("p", name, BubbleContainer, "name-text")
    createNode("p", amount+'€', BubbleContainer, "amount-text")
    createNode("p", date, BubbleContainer, "date-text")
    createNode("a",'Hier gehts zum Dashboard', BubbleContainer, "button-text")
    container.appendChild(BubbleContainer)
}

const loadData = async () => {
    // Fetching vom JSON
    const response = await fetch('./budget.json');
    const array = await response.json();

    // Creating von den einzelnen Divs für die Cost-/Forecast-List
    array.map((item) => {
        createBubbleContainer(item, BudgetContainer)
    })

}

loadData().then((message) => { // We don't return something
    console.log('This is a' + message) // Promises did not fail
}).catch((message) => {
        console.log('This is a' + message) // Promises did fail
    }
)




