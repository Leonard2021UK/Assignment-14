// Select input field
let chatInputField = document.getElementById("textInput");
// Get channel ID from the input fields DATA attribute
let activeChannelId = chatInputField.getAttribute("data-channelId");
// Get username from the session storage
let userName = window.JSON.parse(sessionStorage.getItem("user"))["name"];

// add event listener on input field, it uploads the comment when the field goes inactive
chatInputField.addEventListener('blur',()=>{
    let reqBody = {
        userInput:chatInputField.value,
        userName:userName,
        activeChannelId:activeChannelId

    }
    const fetchOption = {
        "method": "POST",
        "headers": {
            "Content-Type": "text/html",
            "Accept": "text/html"
        },
        body:JSON.stringify(reqBody)
    };
    fetch("/channels",fetchOption)
        .then(() => document.getElementById("textInput").value = "");
})
// Auto refresh all comments
setInterval((activeChannelId)=> {

    // fetch all comments
    fetch("/channels/" + activeChannelId + "/history")

        .then(async (response)=>{
            // insert the returned fragment
            document.getElementById("chatContainer").innerHTML = await response.text();
        })

},500,activeChannelId)
