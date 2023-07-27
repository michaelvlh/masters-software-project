/* TO USE:
* 1. add script currencyEX.js and money.js to html file
* 2. change the id of the element holding the price to "price"
* 3. add a hidden element holding the currency code of the project in the form "GBP" or "USD" etc. with id="currency"
* 4. Example of usage in project_detail.html
*/
(async function () {
    async function fetchJson(url) {
        let res = await fetch(url);

        return await res.json();
    }

    let location = await fetchJson('https://api.ipdata.co?api-key=2a3671033ad62fedee1dc1ec8024bce8f95e875204d5be123a744675&fields=currency');
    let exchange = await fetchJson('https://openexchangerates.org/api/latest.json?app_id=fb739f94ae2348b981aa2c03e418d989');

    fx.base = exchange.base;
    fx.rates = exchange.rates;

    let tbody = document.getElementById('price');
    let curr = document.getElementById('currency');

    if (location.curr != null) {
        tbody.innerHTML = location.currency.symbol + '' +
            fx(tbody.innerHTML).from(curr.innerHTML).to(location.currency.code).toFixed(2);
    }
})();