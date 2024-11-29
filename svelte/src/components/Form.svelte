<script>

    import {updateRecipeStorage} from "$lib/storage.ts";

    let props = $props();
    let selectedItem = $state({})

    function selectRecipe(name) {
        selectedItem = props.data.find(item => item.name === name);
        updateRecipeStorage({name: selectedItem.name})
    }
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
    <div class="recipe-buttons-wrapper">
        <button class="recipe-buttons" onclick={() => {selectedItem = {}}} >Edit</button>
        <button class="recipe-buttons">Cook</button>
    </div>
</div>