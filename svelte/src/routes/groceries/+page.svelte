<script>
    import "../../styles/frame.css"
    import "../../styles/header.css"
    import "../../styles/footer.css"

    import {goto} from "$app/navigation";
    import {onMount} from "svelte";
    import {readDataFromSessionStorage} from "$lib/storage.ts";

    let recipes = [];
    let groceries;


    function navigate() {
        goto('/');
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
                <li>
                    <h4>{grocery.name}</h4>
                    <h4>{grocery.amount}</h4>
                </li>
            {/each}
        </ul>
    </div>
    <footer>
    </footer>
</div>