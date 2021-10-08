const saveNewUser = async (userName)=>{

    let reqBody = {
        name:userName
    }
    const fetchOption = {
        "method": "POST",
        "headers": {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body:JSON.stringify(reqBody)
    };

    const url = "http://localhost:8080";

    if(userName !== null){
        return await fetch(url,fetchOption);
    }

    return Promise.reject(null);
}


if(window.sessionStorage.getItem("user") !== null){
    // console.log("helloo")
    // let fetchOption = {
    //     "method":"GET",
    //     "headers": {
    //         "Accept": "text/html"
    //     }
    // }
    // fetch("http://localhost:8080/channels",fetchOption);
    window.location.href = "/channels";
}else{
    let userName = prompt("What's your name");

    saveNewUser(userName)
        .then(async (response)=>{
            let parsedResponse = await response.json();
            if(parsedResponse !== null){
                window.sessionStorage.setItem("user",JSON.stringify(parsedResponse))
            }
        })
        .then(() => window.location.href = "/channels")
}


