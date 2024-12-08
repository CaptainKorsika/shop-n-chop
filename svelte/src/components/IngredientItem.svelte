<script>

    import "../styles/ingredientItem.css"


    export let ingredient;
    export let deleteCallbackFunction;
    let isEditable = false;

    function toggleEditable() {
        isEditable = !isEditable;
    }


    async function confirmItemEdit() {

        const unit = document.getElementById("unit-input").value
        const category = document.getElementById("category-input").value
        const durability = document.getElementById("durability-input").value

        const ingredientDTO = {
            name: ingredient.name,
            unit: unit,
            category: category,
            durability: durability
        }

        console.log(ingredientDTO)

        await fetch(`http://localhost:8080/shopnchop/ingredient`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(ingredientDTO)
        });

        ingredient = ingredientDTO
        toggleEditable()
    }

</script>

{#if isEditable}
    <div class="edit-ingredient-wrapper">
        <h4>{ingredient.name}</h4>
        <input id="unit-input" class="edit-ingredient-input" type="text" value={ingredient.unit}>
        <input id="category-input" class="edit-ingredient-input" type="text" value={ingredient.category}>
        <input id="durability-input" class="edit-ingredient-input" type="text" value={ingredient.durability}>
        <div class="item-buttons-wrapper">
            <button class="item-buttons" onclick={toggleEditable}>Cancel</button>
            <button class="item-buttons" onclick={confirmItemEdit}>Confirm</button>
        </div>

    </div>

{:else}
    <li>
        <div class="ingredient-item-wrapper">
            <h4>{ingredient.name}</h4>
            <h4>{ingredient.unit}</h4>
            <h4>{ingredient.category}</h4>
            <h4>{ingredient.durability}</h4>
        </div>

        <div class="item-buttons-wrapper">
            <button class="item-buttons" onclick={toggleEditable}>Edit</button>
            <button class="item-buttons" onclick={deleteCallbackFunction}>Trash</button>
        </div>
    </li>
{/if}