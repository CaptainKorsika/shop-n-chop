<script>
    import "../../styles/frame.css"
    import "../../styles/header.css"
    import "../../styles/footer.css"

    import {goto} from "$app/navigation";
    import {onMount} from "svelte";

    let allIngredients;

    onMount(async () => {
        const response = await fetch("http://localhost:8080/shopnchop/ingredients")
        allIngredients = await response.json()
    })

    function navigate() {
        goto('/');
    }
</script>



<div class="frame">
    <header>
        <button>Add Ingredient</button>
        <button onclick={navigate}>Go Back</button>
    </header>
    <div class="main-content-wrapper">
        <ul>
            {#each allIngredients as ingredient}
                <li>
                    {ingredient.name}
                    <button>Edit</button>
                    <button>Trash</button>
                </li>
            {/each}
        </ul>
    </div>
    <footer>

    </footer>
</div>