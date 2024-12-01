<script>
    import "../../styles/frame.css"
    import "../../styles/header.css"
    import "../../styles/footer.css"
    import {goto} from "$app/navigation";
    import {onMount} from "svelte";

    let allRecipes;

    onMount(async () => {
        const response = await fetch("http://localhost:8080/shopnchop/recipes")
        allRecipes = await response.json()
    })

    function navigate() {
        goto('/');
    }
</script>



<div class="frame">
    <header>
        <button class="create-recipe">Create New Recipe</button>
        <button onclick={navigate}>Go Back</button>
    </header>
    <div class="main-content-wrapper">
        <ul>
            {#each allRecipes as recipe}
                <li>
                    {recipe.name}
                </li>
            {/each}
        </ul>
    </div>
    <footer>

    </footer>
</div>