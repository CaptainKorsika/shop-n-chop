<script>
    import "../../styles/frame.css"
    import "../../styles/header.css"
    import "../../styles/footer.css"
    import "../../styles/groceries.css"

    import {goto} from "$app/navigation";
    import {onMount} from "svelte";
    import {readDataFromSessionStorage} from "$lib/storage.ts";

    let recipes = [];
    let groceries;


    function navigate() {
        goto('/');
    }

    function confirmPurchase() {
        const confirmedPurchases = [];
        const today = new Date()

        const day = String(today.getDate()).padStart(2, '0');
        const month = String(today.getMonth() + 1).padStart(2, '0');
        const year = today.getFullYear();

        const todayFormattedString = `${day}.${month}.${year}`;


        groceries.forEach(grocery => {
            grocery.amount = document.getElementById(grocery.name).value
            confirmedPurchases.push({
                name: grocery.name,
                amount: grocery.amount,
                unit: grocery.unit,
                purchaseDate: todayFormattedString
            })
        })

        fetch('http://localhost:8080/shopnchop/groceries', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(confirmedPurchases)
        });

        navigate()

    }


    onMount(async () => {

        const storageData = readDataFromSessionStorage()


        storageData.forEach(recipe => {
            recipes.push(recipe.selectedRecipe)
        })


        const response = await fetch(`http://localhost:8080/shopnchop/calculatedGroceries`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(recipes)
        });
        groceries = await response.json()


    })
</script>



<div class="frame">
    <header>
        <button onclick={navigate}>Go Back</button>
    </header>
    <div class="main-content-wrapper">
        <ul>
            {#each groceries as grocery}
                <li class="grocery-item">
                    <h4>{grocery.name}:</h4>
                    <div class="groceries-amount-wrapper">
                        <input id={grocery.name} class="grocery-amount-input" type="text" value={grocery.amount}>
                        <h4>{grocery.unit}</h4>
                    </div>

                </li>
            {/each}
        </ul>
    </div>
    <footer>
        <button onclick={confirmPurchase}>Confirm Purchase</button>
    </footer>
</div>