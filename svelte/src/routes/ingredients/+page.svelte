<script>
    import "../../styles/frame.css"
    import "../../styles/header.css"
    import "../../styles/footer.css"
    import "../../styles/ingredients.css"

    import {goto} from "$app/navigation";
    import {onMount} from "svelte";
    import IngredientItem from "../../components/IngredientItem.svelte";

    let allIngredients;

    onMount(async () => {
        const response = await fetch("http://localhost:8080/shopnchop/ingredients")
        allIngredients = await response.json()
    })


    async function deleteIngredient(ingredient) {
        const response = await fetch(`http://localhost:8080/shopnchop/deleteIngredient/${ingredient.name}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            }
        });
        allIngredients = await response.json()
    }


    function navigate(name = "/") {
        goto(`${name}`);
    }
</script>



<div class="frame">
    <header>
        <button onclick={() => {navigate("addIngredients")}}>Add Ingredient</button>
        <button onclick={() => {navigate()}}> Go Back</button>
    </header>
    <div class="main-content-wrapper">
        <ul>
            {#each allIngredients as ingredient}
                <IngredientItem
                        ingredient={ingredient}
                        deleteCallbackFunction={() => {deleteIngredient(ingredient)}}
                />
            {/each}
        </ul>
    </div>
    <footer>
    </footer>
</div>