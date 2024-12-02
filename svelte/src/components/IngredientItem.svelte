<script>
    export let ingredient;
    let isEditable = false;

    function toggleEditable() {
        isEditable = !isEditable;
    }

</script>

{#if isEditable}
    <p>Test</p>
    <button onclick={toggleEditable}>Cancel</button>
{:else}
    <li>
        <h4>{ingredient.name}</h4>
        <h4>{ingredient.unit}</h4>
        <h4>{ingredient.category}</h4>
        <h4>{ingredient.durability}</h4>
        <div>
            <button class="item-buttons" onclick={toggleEditable}>Edit</button>
            <button class="item-buttons">Trash</button>
        </div>
    </li>
{/if}