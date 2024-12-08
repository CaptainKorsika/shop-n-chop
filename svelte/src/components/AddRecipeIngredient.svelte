<script>
    import "../styles/addRecipe.css"

    let isInDatabase = true;
    let ingredientUnit = ""

    export let recipeIngredientDto = {
        name: null,
        amount: null,
        unit: ingredientUnit
    };

    function createIngredient(event) {
        const name = event.target.closest(".ingredient-list-item").querySelector("input[placeholder='Name']").value
        const unit = event.target.closest(".ingredient-list-item").querySelector("input[placeholder='Unit']").value
        const category = event.target.closest(".ingredient-list-item").querySelector("input[placeholder='Category']").value
        const durability = event.target.closest(".ingredient-list-item").querySelector("input[placeholder='Durability']").value

        if (name.length !== 0
            && unit.length !== 0
            && category.length !== 0
            && durability.length !== 0
        ) {

            const ingredientDto = {
                name: name,
                unit: unit,
                category: category,
                durability: durability
            }

            fetch(`http://localhost:8080/shopnchop/ingredient`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(ingredientDto)
            });
            ingredientUnit = unit
            recipeIngredientDto.unit = ingredientUnit
            isInDatabase = true;
        }
    }

    function updateDto(value, placeholder) {
        const lowerCasePlaceholder = placeholder.toLowerCase()

        if (lowerCasePlaceholder === "name") {
            recipeIngredientDto.name = value
        } else if (lowerCasePlaceholder === "amount") {
            recipeIngredientDto.amount = value
        }
    }


    async function validateIngredient(value) {

        if (value.length !== 0) {

            try{
                const response = (await fetch(`http://localhost:8080/shopnchop/ingredient/${value}`))
                const ingredientFromDatabase = await response.json()
                ingredientUnit = ingredientFromDatabase.unit
                recipeIngredientDto.unit = ingredientUnit
                isInDatabase = true
            } catch (e) {
                isInDatabase = false
            }


        }

    }
</script>




<li class="ingredient-list-item">
    <input id="ingredient-name" class="ingredient-input-for-recipe" type="text" placeholder="Name" onfocusout={() => {
        validateIngredient(event.target.value)
        updateDto(event.target.value, event.target.placeholder)
    }}>
    <input class="ingredient-input-for-recipe" type="text" placeholder="Amount" onfocusout={() => {updateDto(event.target.value, event.target.placeholder)}}>
    {#if isInDatabase}
        <h4>{ingredientUnit}</h4>
    {:else}
        <input class="ingredient-input-for-recipe" type="text" placeholder="Unit">
        <input class="ingredient-input-for-recipe" type="text" placeholder="Category">
        <input class="ingredient-input-for-recipe" type="text" placeholder="Durability">
        <button class="ingredient-confirm-button" onclick={() => {createIngredient(event)}}>+</button>
    {/if}
</li>