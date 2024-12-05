<script>

    import {goto} from "$app/navigation";

    export let recipe;


</script>





<div class="recipe-preview-wrapper" onclick={() => {goto(`/recipes/${recipe.name}`)}}>
    {recipe.name}
    {recipe.duration}
</div>