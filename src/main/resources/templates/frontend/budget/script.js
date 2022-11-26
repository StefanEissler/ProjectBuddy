const BudgetContainer = document.getElementById("budget-bubbles"); // Reference div id=container


const createNode = (datatype, text, container, className) => {
    const tag = document.createElement(datatype) // Parent
    const textNode = document.createTextNode(text) // Child
    tag.className = className ? className : ""
    tag.appendChild(textNode)
    container.appendChild(tag)
}
const createClickNode = (datatype, text, container, className, onClick) => {
    const tag = document.createElement(datatype)
    const textNode = document.createTextNode(text) // Child
    tag.addEventListener("click",onClick);
    tag.className = className ? className : ""
    tag.appendChild(textNode)
    container.appendChild(tag)
}

const createBubbleContainer = (object, container) => {

    const { id,  name, date, amount } = object

    const BubbleContainer = document.createElement("div")
    BubbleContainer.className = "bubble-container"
    const BubbleContainerSub = document.createElement("div")
    BubbleContainerSub.className = "bubble-container-sub"

    createNode("h3", name, BubbleContainer, "name-text")
    BubbleContainer.appendChild(BubbleContainerSub);

    createNode("div", amount+'€', BubbleContainerSub, "amount-text")
    createNode("div", date, BubbleContainerSub, "date-text")
    createClickNode("button",'Hier gehts zum Dashboard', BubbleContainer, "button-text", ()=>{
        window.location.href="../analysis/?user=1&budget="+id; //User hardgecoded
    })
    container.appendChild(BubbleContainer)
}

const loadData = async () => {
    // Fetching vom JSON
    const response = await fetch("http://localhost:8080/api/user/1/budgets");
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




