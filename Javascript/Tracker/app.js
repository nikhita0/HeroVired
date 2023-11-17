async function searchRepositories() {
    var searchTerm = document.getElementById('searchInput').value;
    try {
      const response = await fetch(`/api/search/repositories?searchTerm=${searchTerm}`);
      const data = await response.json();
  
      const searchResults = document.getElementById('searchResults');
      searchResults.innerHTML = ''; // Clear previous search results
  
      if (data && data.items.length > 0) {
        const resultList = document.createElement('ul');
        data.items.forEach((item) => {
          const listItem = document.createElement('li');
          listItem.textContent = item.full_name;
          resultList.appendChild(listItem);
        });
        searchResults.appendChild(resultList);
      } else {
        searchResults.innerHTML = '<p>No results found</p>';
      }
    } catch (error) {
      console.error('Error:', error);
    }
  }
  