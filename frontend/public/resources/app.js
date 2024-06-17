document.addEventListener('DOMContentLoaded', () => {
    const getDataButton = document.getElementById("get-data-button");
    const dataDisplay = document.getElementById("data-display");

    function updateDisplay() {
        console.log("Updating display...");
        const url = 'https://api-test.intellect37.com/api/hello';

        // Clear previous content
        dataDisplay.textContent = '';

        // Username and password for Basic Authentication
        const username = 'admin';
        const password = 'password';

        // Create a basic auth header
        const headers = new Headers();
        headers.append('Authorization', 'Basic ' + btoa(username + ':' + password));

        // Call `fetch()`, passing in the URL and headers.
        fetch(url, {
            method: 'GET',
            headers: headers
        })
        .then((response) => {
            if (!response.ok) {
                throw new Error(`HTTP error: ${response.status}`);
            }
            return response.json();
        })
        .then((json) => {
            // Prettify JSON output
            const prettyJson = JSON.stringify(json, null, 2);
            // Display prettified JSON in a <pre> tag to preserve formatting
            dataDisplay.textContent = prettyJson;
        })
        .catch((error) => {
            dataDisplay.textContent = `Could not fetch data: ${error}`;
        });
    }

    getDataButton.addEventListener('click', updateDisplay);
});
