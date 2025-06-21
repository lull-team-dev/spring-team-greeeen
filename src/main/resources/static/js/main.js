
document.addEventListener('DOMContentLoaded', () => {
	const searchInput = document.getElementById('search-input');
	const searchButton = document.getElementById('search-button');
	const itemList = document.getElementById('item-list');

	function createItemCard(item) {
		return `
			<div class="item-card">
				<p class="ranking">${item.ranking ?? ''}</p>
				<div class="nameAndDetail">
					<h1>${item.name}</h1>
					<div class="item-detail">
						<div class="featureAndPrice">
							<p class="feature">${item.feature}</p>
							<p class="price">${item.price}円</p>
						</div>
						<img src="/images/${item.imagePath}" alt="商品画像">
					</div>
				</div>
                <button class="add-to-cart-button" data-id="${item.id}">カートに追加</button>
			</div>
		`;
	}

	function searchItems() {
		const keyword = searchInput.value;
		fetch('/api/items/search', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({ keyword: keyword })
		})
			.then(res => res.json())
			.then(items => {
				itemList.innerHTML = '';
				items.forEach(item => {
					const itemCard = createItemCard(item);
					itemList.insertAdjacentHTML('beforeend', itemCard);
				});
			})
			.catch(error => {
				console.error('検索失敗:', error);
			});
	}

	searchButton.addEventListener('click', searchItems);
	searchInput.addEventListener('keydown', e => {
		if (e.key === 'Enter') searchItems();
	});
});







function categorySearch() {
	const buttons = document.querySelectorAll('button');
	buttons.forEach(button => {
		button.addEventListener('click', () => {
			const categoryId = button.dataset.id;
			fetch('/category/items', {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({ categoryId: categoryId })
			})
				.then(response => response.json())
				.then(items => {
					const list = document.getElementById('item-list');
					list.innerHTML = '';
					items.forEach(item => {
						const itemCard = `
							<div class="item-card">
								<p class="ranking">${item.ranking ?? ''}</p>
								<div class="nameAndDetail">
									<h1>${item.name}</h1>
									<div class="item-detail">
										<div class="featureAndPrice">
											<p class="feature">${item.feature}</p>
											<p class="price">${item.price}円</p>
										</div>
										<img src="/images/${item.imagePath}" alt="商品画像">
									</div>
								</div>
                                <button class="add-to-cart-button" data-id="${item.id}">カートに追加</button>
							</div>
						`;
						list.insertAdjacentHTML('beforeend', itemCard);
					});
				})
				.catch(error => {
					console.error('カテゴリ取得失敗:', error);
				});
		});
	});
}

document.addEventListener('DOMContentLoaded', () => {
	fetch('/api/items/all')
		.then(response => response.json())
		.then(items => {
			const list = document.getElementById('item-list');
			list.innerHTML = '';
			items.forEach(item => {
				const itemCard = `
					<div class="item-card">
						<p class="ranking">${item.ranking ?? ''}</p>
						<div class="nameAndDetail">
							<h1>${item.name}</h1>
							<div class="item-detail">
								<div class="featureAndPrice">
									<p class="feature">${item.feature}</p>
									<p class="price">${item.price}円</p>
								</div>
								<img src="/images/${item.imagePath}" alt="商品画像">
							</div>
						</div>
                                                        <button class="add-to-cart-button" data-id="${item.id}">カートに追加</button>

					</div>
				`;
				list.insertAdjacentHTML('beforeend', itemCard);
			});
		})
		.catch(error => {
			console.error('初回商品取得失敗:', error);
		});

	categorySearch();
});


function setupAddToCartButton(){
    document.querySelectorAll('.add-to-cart-button'.forEach(button => {
        button.addEventListener('click', () => {
const itemId = button.dataset.id;
fetch('/api/cart/add',{
    method:'POST',
    headers:{
        'Content-Type' : 'application/json'
    },
    body : JSON.stringify({itemId : itemId})
})
.then(response => response.json())
.then(cartItems => {
    renderCart(cartItems);
} )
.catch(error => {
    console.error('カート追加失敗：',error);
})
        });
    }));
}


function renderCart(cartItems){
    const cart = document.getElementById('cart-item');
    cart.innerHTML = '';
    cartItems.forEach(item => {
    const html =`
    <div class="cart-row">
        <img src="/images/${item.imagePath}" alt="${item.name}" class="cart-img">
        <span>${item.name}</span>
        <span>1 ×</span>
        <span>${item.price}円</span>
      </div>
    `;
    cart.insertAdjacentHTML('beforeend',html);
    });
}


