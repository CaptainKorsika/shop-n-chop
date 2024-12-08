<script>
    import "../styles/header.css"
    import {goto} from "$app/navigation";

    function navigate(route) {
         goto(`/${route}`);
    }
</script>



<header class="main-header">
    <div class="button-section">
        <div class="recipe-section">
            <button class="show-recipes" on:click={() => {navigate("recipes")}}>Manage Recipes</button>
        </div>
        <div class="ingredient-section">
            <button on:click={() => {navigate("ingredients")}}>Manage Ingredients</button>
        </div>
    </div>
    <button class="show-fridge" on:click={() => {navigate("fridge")}}>Open Fridge</button>
</header>