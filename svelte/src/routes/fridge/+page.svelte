<script>

    import "../../styles/frame.css"
    import "../../styles/header.css"
    import "../../styles/footer.css"
    import "../../styles/fridge.css"
    import {onMount} from "svelte";
    import {goto} from "$app/navigation";

    let allGroceries;
    let expiredGroceries;
    let onlyExpired = false

    onMount(async () => {
        const groceriesResponse = await fetch("http://localhost:8080/shopnchop/groceries")
        allGroceries = await groceriesResponse.json()

        await updateExpired()

    })

    function navigate() {
        goto('/');
    }

    async function deleteGrocery(grocery) {
        const deletableItems = [grocery]

        const response = await fetch('http://localhost:8080/shopnchop/deleteGroceries', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(deletableItems)
        });

        allGroceries = await response.json()
        await updateExpired()
    }

    async function deleteAllExpired(groceryList) {
        const response = await fetch('http://localhost:8080/shopnchop/deleteGroceries', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(groceryList)
        });

        allGroceries = await response.json()
        await updateExpired()
    }

    async function updateExpired() {
        const expiredResponse = await fetch("http://localhost:8080/shopnchop/expired")
        expiredGroceries = await expiredResponse.json()
    }

</script>


<div class="frame">
    <header>
        <button on:click={() => {onlyExpired = !onlyExpired}}>Toggle Expired Only</button>
        <button on:click={navigate}>CloseFridge</button>
    </header>
    <div class="main-content-wrapper">
        <ul>
            {#if !onlyExpired}
                {#each allGroceries as grocery}
                    <li>
                        <div class="amount-wrapper">
                            <h4>{grocery.amount}</h4>
                            <h4>{grocery.unit}</h4>
                        </div>
                        <h4>{grocery.name}</h4>
                        <h4>Expiration:  { grocery.currentExpirationDate}</h4>
                        <div>
                            <button class="grocery-edit-button">Edit</button>
                            <button class="grocery-edit-button" on:click={() => deleteGrocery(grocery)}>Trash</button>
                        </div>
                    </li>
                {/each}
            {:else}
                {#each expiredGroceries as grocery}
                    <li>
                        <div class="amount-wrapper">
                            <h4>{grocery.amount}</h4>
                            <h4>{grocery.unit}</h4>
                        </div>
                        <h4>{grocery.name}</h4>
                        <h4 style="color: #a90000">Expiration: &ensp; {grocery.currentExpirationDate}</h4>
                        <div>
                            <button class="grocery-edit-button">Edit</button>
                            <button class="grocery-edit-button" on:click={() => deleteGrocery(grocery)}>Trash</button>
                        </div>
                    </li>
                {/each}
            {/if}
        </ul>
    </div>
    <footer>
        {#if onlyExpired}
            <button on:click={() => deleteAllExpired(expiredGroceries)}>Throw away Expired</button>
        {/if}
    </footer>
</div>