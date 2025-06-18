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
				console.log(item); 
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
