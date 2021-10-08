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
        // .then(()=>window.location.href = "/channels/" + activeChannelId)
})

setInterval((activeChannelId)=> {

    fetch("/channels/" + activeChannelId + "/history")

        .then(async (response)=>{
            // $.get("event-count").done(function(fragment) { // get from controller
            //     $("#eventCount").replaceWith(fragment); // update snippet of page
            // });
            // console.log()
            document.getElementById("chatContainer").innerHTML=await response.text();
            // // chatContainer.innerHTML = '';
            // const parser = new DOMParser();
            // const doc = parser.parseFromString(await response.text(), 'text/html');
            // $('#chatContainer').replaceWith(doc.body);

            // console.log(doc)
            // chatContainer.appendChild( doc.body);
        })
    // window.location.href = "/channels/" + activeChannelId;
},500,activeChannelId)
