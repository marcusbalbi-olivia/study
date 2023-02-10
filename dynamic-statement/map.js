const inputData = require("./input.json");
const lf = require("./lf.json");


const appleLf = lf[0];

function compare(fieldValue, operator, value) {
    switch (operator) {
        case '>': return fieldValue > value;
        case '<': return fieldValue < value;
        case '>=': return fieldValue >= value;
        case '<=': return fieldValue <= value;
        case '=': return fieldValue == value;
        case '!=': return fieldValue != value;
        case 'contains': return `${fieldValue}`.toLowerCase().includes(value.toLowerCase());
    }
}

const foundInput = inputData.filter((input) => {
    const compareResults = [];
    for (const cond of appleLf.conditions) {
        if (input[cond.field_name]) {
            compareResults.push(compare(input[cond.field_name], cond.operator, cond.value))
        }
    }
    if (compareResults.length === 0)    return false;
    return compareResults.every(r => r === true);
});

console.log(foundInput);