function categorysearch() {
    const buttons = document.querySelectorAll('button');
    buttons.forEach(button => {
        button.addEventListener('click', () => {
            const categoryId = button.dataset.id;
            fetch('/items', {
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
                        <div class = "item-card" >
                        <p class="ranking">${item.ranking}</p>
                        <div class="nameAndDetail">
                        <h1>${item.name}</h1>
                        <div class="item-detail">
                        <div class="featureAndPrice">
                        <p class = "feature">${item.feature}</p>
                        <p class="price">${item.price}</p>
                        </div>
                        <img src="${item.image_path}" alt="商品画像">
                        </div>
                        </div>
                        </div>
                        `;
                        list.insertAdjacentHTML('beforeend', itemCard);
                    });
                })
                .catch(error => {
                    console.error('エラー：', error);

                });
        });
    });
}


document.addEventListener('DOMContentLoaded', categorySearch);
