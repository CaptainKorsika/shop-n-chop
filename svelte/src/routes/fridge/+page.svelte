<script>



    import "../../styles/main-frame.css"
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
    <div>
        <button on:click={() => {onlyExpired = !onlyExpired}}>Toggle Expired Only</button>
        <button on:click={navigate}>CloseFridge</button>
    </div>
    <div class="main-content-wrapper">
        <ul>
            {#if !onlyExpired}
                {#each allGroceries as grocery}
                    <li>
                        <h3>{grocery.amount}</h3>
                        <h3>{grocery.name}</h3>
                        <h3>Expiration: &ensp; { grocery.currentExpirationDate}</h3>
                        <div>
                            <button class="grocery-edit-button">Edit</button>
                            <button class="grocery-edit-button" on:click={() => deleteGrocery(grocery)}>Trash</button>
                        </div>

                    </li>
                {/each}
            {:else}
                {#each expiredGroceries as grocery}
                    <li>
                        <h3>{grocery.amount}</h3>
                        <h3>{grocery.name}</h3>
                        <h3 style="color: #a90000">Expiration: &ensp; {grocery.currentExpirationDate}</h3>
                        <div>
                            <button class="grocery-edit-button">Edit</button>
                            <button class="grocery-edit-button" on:click={() => deleteGrocery(grocery)}>Trash</button>
                        </div>
                    </li>
                {/each}
            {/if}
        </ul>
    </div>
    {#if onlyExpired}
        <button on:click={() => deleteAllExpired(expiredGroceries)}>Throw away Expired</button>
    {/if}
</div>