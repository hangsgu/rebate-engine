var productsData = null;
var rulesData = null;
var productId = null;
var cartData = null;
var baseURL = "../api/v1/";
var catalogListURL = baseURL + "catalogItems";
var rebateRulesURL = baseURL + "activeRebateRules";
var cartURL = baseURL + "cart";
var cartCheckoutURL = cartURL + "/checkout";

function getData() {
	sendGetRequest(catalogListURL, productsDataCallBack);
	sendGetRequest(rebateRulesURL, rebaterulesDataCallBack);
	sendGetRequest(cartURL, setCartOnLoad);
	// addRowHandlers()
	// addCheckoutHandler();

}

function CheckoutHandler() {

	sendPostRequest(cartCheckoutURL, handleCheckoutResponse);

}

function handleCheckoutResponse() {
	var checkoutResponse = JSON.parse(this.response);

	var table = document.getElementById("cart");
	var oRows = table.getElementsByTagName('tr');
	if (oRows !== null) {
		var rowCount = oRows.length;
		for (var x = rowCount - 1; x > 0; x--) {
			table.deleteRow(x);
		}
	}
	if (checkoutResponse.checkoutResult === true) {
		alert("Cart checked out successfully");
		document.getElementById("itemTotal").value = "";
		document.getElementById("cartRebateAmount").value = "";
		document.getElementById("cartRebateReason").value = "";
		document.getElementById("finalAmount").value = "";
	}

}

function sendGetRequest(url, callback) {
	// var oReq = createCORSRequestAndSend("GET", url , callback)
	// var oReq = new XMLHttpRequest();
	var oReq = new XMLHttpRequest();
	oReq.addEventListener("load", callback);
	oReq.open("GET", url, true);
	oReq.send();

}

function sendPostRequest(url, callback) {
	var oReq = new XMLHttpRequest();
	oReq.addEventListener("load", callback);
	oReq.open("POST", url, true);
	oReq.send();

}

function sendDeleteRequest(url, callback) {
	var oReq = new XMLHttpRequest();
	oReq.addEventListener("load", callback);
	oReq.open("DELETE", url, true);
	oReq.send();

}

function addItemToCart(itemID, quantity) {
	var addItemURL = cartURL + "/items/" + itemID + "?quantityToAdd="
			+ quantity;
	sendPostRequest(addItemURL, addToCartCallbackHandler);
}

function addToCartCallbackHandler() {

	var cartData = JSON.parse(this.response);
	var cartItems = cartData.cartItems;
	// catalogItems = productsData;
	var table = document.getElementById("cart");
	var oRows = table.getElementsByTagName('tr');
	if (oRows !== null) {
		var rowCount = oRows.length;
		for (var x = rowCount - 1; x > 0; x--) {
			table.deleteRow(x);
		}
	}

	for (var i = 0, len = cartItems.length; i < len; ++i) {
		var row = table.insertRow(i + 1);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);
		var cell4 = row.insertCell(3);
		var cell5 = row.insertCell(4);
		var cell6 = row.insertCell(5);
		var cell7 = row.insertCell(6);
		var cell8 = row.insertCell(7);

		cell1.innerHTML = cartItems[i].id;
		cell2.innerHTML = cartItems[i].category;
		cell3.innerHTML = cartItems[i].name;
		cell4.innerHTML = cartItems[i].standardPrice;
		cell5.innerHTML = cartItems[i].quantity;
		cell6.innerHTML = cartItems[i].standardTotal;
		cell7.innerHTML = cartItems[i].rebateAmount;
		cell8.innerHTML = cartItems[i].rebateReason;
		var createClickHandler = function(row) {
			return function() {
				var cell = row.getElementsByTagName("td")[0];
				var id = cell.innerHTML;
				productId = id;
				deleteItemFromCart(id, 1);

			};
		};

		row.onclick = createClickHandler(row);
	}
	document.getElementById("itemTotal").value = cartData.itemTotal;
	document.getElementById("cartRebateAmount").value = cartData.cartRebateAmount;
	if (cartData.cartRebateReason != null) {
		document.getElementById("cartRebateReason").value = cartData.cartRebateReason;
	}
	document.getElementById("finalAmount").value = cartData.finalAmount;

}

function deleteItemFromCart(itemID, quantity) {
	var oReq = new XMLHttpRequest();
	var deleteItemURL = cartURL + "/items/" + itemID + "?quantityToRemove="
			+ quantity;
	sendDeleteRequest(deleteItemURL, deleteItemFromCartCallbackHandler);

}

function deleteItemFromCartCallbackHandler() {
	var cartData = JSON.parse(this.response);
	var cartItems = cartData.cartItems;
	// catalogItems = productsData;
	var table = document.getElementById("cart");
	var oRows = table.getElementsByTagName('tr');
	if (oRows !== null) {
		var rowCount = oRows.length;
		for (var x = rowCount - 1; x > 0; x--) {
			table.deleteRow(x);
		}
	}

	for (var i = 0, len = cartItems.length; i < len; ++i) {
		var row = table.insertRow(i + 1);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);
		var cell4 = row.insertCell(3);
		var cell5 = row.insertCell(4);
		var cell6 = row.insertCell(5);
		var cell7 = row.insertCell(6);
		var cell8 = row.insertCell(7);

		cell1.innerHTML = cartItems[i].id;
		cell2.innerHTML = cartItems[i].category;
		cell3.innerHTML = cartItems[i].name;
		cell4.innerHTML = cartItems[i].standardPrice;
		cell5.innerHTML = cartItems[i].quantity;
		cell6.innerHTML = cartItems[i].standardTotal;
		cell7.innerHTML = cartItems[i].rebateAmount;
		cell8.innerHTML = cartItems[i].rebateReason;
		var createClickHandler = function(row) {
			return function() {
				var cell = row.getElementsByTagName("td")[0];
				var id = cell.innerHTML;
				productId = id;
				deleteItemFromCart(id, 1);

			};
		};

		row.onclick = createClickHandler(row);
	}

	document.getElementById("itemTotal").value = cartData.itemTotal;
	document.getElementById("cartRebateAmount").value = cartData.cartRebateAmount;
	if (cartData.cartRebateReason != null) {
		document.getElementById("cartRebateReason").value = cartData.cartRebateReason;
	}
	document.getElementById("finalAmount").value = cartData.finalAmount;

}

function setCartOnLoad() {

	if (this.readyState == XMLHttpRequest.DONE && this.status === 200) {
		cartData = JSON.parse(this.response);
		var cartItems = cartData.cartItems;
		// catalogItems = productsData;
		var table = document.getElementById("cart");
		var oRows = table.getElementsByTagName('tr');
		if (oRows !== null) {
			var rowCount = oRows.length;
			for (var x = rowCount - 1; x > 0; x--) {
				table.deleteRow(x);
			}
		}

		for (var i = 0, len = cartItems.length; i < len; ++i) {
			var row = table.insertRow(i + 1);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			var cell4 = row.insertCell(3);
			var cell5 = row.insertCell(4);
			var cell6 = row.insertCell(5);
			var cell7 = row.insertCell(6);
			var cell8 = row.insertCell(7);

			cell1.innerHTML = cartItems[i].id;
			cell2.innerHTML = cartItems[i].category;
			cell3.innerHTML = cartItems[i].name;
			cell4.innerHTML = cartItems[i].standardPrice;
			cell5.innerHTML = cartItems[i].quantity;
			cell6.innerHTML = cartItems[i].standardTotal;
			cell7.innerHTML = cartItems[i].rebateAmount;
			cell8.innerHTML = cartItems[i].rebateReason;
			var createClickHandler = function(row) {
				return function() {
					var cell = row.getElementsByTagName("td")[0];
					var id = cell.innerHTML;
					productId = id;
					deleteItemFromCart(id, 1);

				};
			};

			row.onclick = createClickHandler(row);
		}

		document.getElementById("itemTotal").value = cartData.itemTotal;
		document.getElementById("cartRebateAmount").value = cartData.cartRebateAmount;
		if (cartData.cartRebateReason != null) {
			document.getElementById("cartRebateReason").value = cartData.cartRebateReason;
		}
		document.getElementById("finalAmount").value = cartData.finalAmount;

	}
}

function productsDataCallBack() {
	// document.write(this.response);
	productsData = JSON.parse(this.response);
	var counter = 1;
	var table = document.getElementById("catalog");
	if (productsData != null) {
		// document.write("productsData is not null. inside callback")

		productsData.forEach(function(e) {
			var row = table.insertRow(counter);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			var cell4 = row.insertCell(3);
			cell1.innerHTML = e.id;
			cell2.innerHTML = e.category;
			cell3.innerHTML = e.name;
			cell4.innerHTML = e.price;
			counter++;
			var that = this;
			var createClickHandler = function(row) {
				return function() {
					var cell = row.getElementsByTagName("td")[0];
					var id = cell.innerHTML;
					productId = id;
					addItemToCart(id, 1);

				};
			};

			row.onclick = createClickHandler(row);
		});
	}

}

function rebaterulesDataCallBack() {
	rulesData = JSON.parse(this.response);
	var table = document.getElementById("rules");
	var rulescounter = 1;
	if (rulesData != null) {
		rulesData.activeRules.forEach(function(e) {
			var row = table.insertRow(rulescounter);
			var cell1 = row.insertCell(0);
			cell1.innerHTML = e;
			rulescounter++;

		});
	}

}
