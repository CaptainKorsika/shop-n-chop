<script>
    import "../styles/recipePreview.css"
    import {goto} from "$app/navigation";

    export let recipe;


</script>





<div class="recipe-preview-wrapper" onclick={() => {goto(`/recipes/${recipe.name}`)}}>
    <h2>{recipe.name}</h2>
</div>