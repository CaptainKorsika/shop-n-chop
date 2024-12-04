<script>

    import {deleteDataFromSessionStorage, readDataFromSessionStorage, updateRecipeStorage} from "$lib/storage.ts";
    import {onMount} from "svelte";

    let props = $props();
    let selectedItem = $state({})

    function selectRecipe(name) {
        selectedItem = props.data.find(item => item.name === name);

        const storageObject = {
            "day": props.day,
            "selectedRecipe": selectedItem
        }
        updateRecipeStorage(storageObject)
    }

    function editSelectedRecipe() {
        deleteDataFromSessionStorage(props.day)
        selectedItem = {}
    }

    function insertDataFromSessionStorage() {
        const storageData = readDataFromSessionStorage()
        const matchingRecipe = storageData.filter(recipe => recipe.day === props.day);


        if (Object.keys(matchingRecipe).length === 0) {
            return {}
        } else {
            return matchingRecipe[0].selectedRecipe
        }
    }

    function cookMeal() {
        let groceries = []
        selectedItem.ingredients.forEach(ingredient => {
            groceries.push({
                name: ingredient.name,
                amount: ingredient.amount,
                unit: ingredient.unit
            })
        })

        fetch('http://localhost:8080/shopnchop/cook', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(groceries)
        });

        editSelectedRecipe()
    }



    onMount(async () => {
        selectedItem = insertDataFromSessionStorage()
    })

</script>

<div class="form-wrapper">
    <form>
        <div class="recipe-selection-wrapper">
            <label for={props.day}>{props.day}:</label>
            {#if props.data}
                {#if Object.keys(selectedItem).length === 0}
                    <select name="{props.day}-recipe" id="{props.day}" onchange={() => selectRecipe(event.target.value, props.day)}>
                        <option selected value=""></option>
                        {#each props.data as item}
                            <option value="{item.name}">{item.name}</option>
                        {/each}
                    </select>
                {:else}
                    <h3>
                        {selectedItem.name}
                    </h3>

                {/if}
            {:else}
                <select name="{props.day}-recipe" id={props.day}>
                </select>
            {/if}
        </div>
    </form>
    <div class="item-buttons-wrapper">
        <button class="item-buttons" onclick={editSelectedRecipe} >Edit</button>
        <button class="item-buttons" onclick={cookMeal} >Cook</button>
    </div>
</div>