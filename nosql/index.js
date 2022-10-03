const { readData } = require("./data.js");

readData('./raw').then(results => {
    console.log(results);
    for(let o of results.orders){
        for(let l of o.lines){
            console.log(l);
        }
    }
})