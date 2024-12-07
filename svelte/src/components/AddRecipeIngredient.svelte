<script>

    let isInDatabase = true;


    async function validateIngredient(value) {

        if (value.length !== 0) {

            try{
                const response = (await fetch(`http://localhost:8080/shopnchop/ingredient/${value}`))
                console.log(await response.json())
                isInDatabase = true

            } catch (e) {
                isInDatabase = false
            }



        }

    }



</script>




<li class="ingredient-list-item">
    <input id="ingredient-name" class="ingredient-input-for-recipe" type="text" placeholder="Name" onfocusout={() => {validateIngredient(event.target.value)}}>
    <input class="ingredient-input-for-recipe" type="text" placeholder="Amount">
    {#if !isInDatabase}
        <input class="ingredient-input-for-recipe" type="text" placeholder="Unit">
        <input class="ingredient-input-for-recipe" type="text" placeholder="Category">
    {/if}
</li>