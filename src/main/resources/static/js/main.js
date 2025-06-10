window.onload = () => {
    fetch('/api/items')
    .then(response => response.json())
    .then(data => {
        const itemList = document.getElementById('item-list');
        data.forEach((item, index) => {
            const itemCard = document.createElement('div');
            itemCard.className = 'item-card';
            itemCard.innerHTML = `
                <div class="ranking">${index + 1}</div>
                <img src="${item.imageUrl}" alt="${item.name}">
                <h3>${item.name}</h3>
                <p class="price">¥${item.price.toLocaleString()}</p>
                <p class="description">${item.description}</p>
            `;
            itemList.appendChild(itemCard);
        });
    })
    .catch(error => {
        console.error('Error fetching items:', error);
    });
}



