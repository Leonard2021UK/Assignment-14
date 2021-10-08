let chatInputField = document.getElementById("textInput");
let activeChannelId = chatInputField.getAttribute("data-channelId");
let userName = window.JSON.parse(sessionStorage.getItem("user"))["name"];

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
        .then(()=>window.location.href = "/channels/" + activeChannelId)
})

setInterval((activeChannelId)=> {

    fetch("/channels/" + activeChannelId + "/history")

        .then(async (response)=>{
            $('#chatContainer').html(await response.text());

            // console.log()
            // let chatContainer = document.getElementById("chatContainer");
            // // chatContainer.innerHTML = '';
            // const parser = new DOMParser();
            // const doc = parser.parseFromString(await response.text(), 'text/html');
            // console.log(doc)
            // chatContainer.appendChild( doc.body);
        })
    // window.location.href = "/channels/" + activeChannelId;
},500,activeChannelId)
