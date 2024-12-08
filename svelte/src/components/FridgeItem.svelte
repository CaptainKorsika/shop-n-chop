<script>
    import "../styles/fridgeitem.css"

    export let grocery;
    export let deleteGrocery;
    export let updateGroceries;

    let isEditable = false


    async function confirmEdit() {
        const amount = document.getElementById("fridgeitem-amount").value
        const date = document.getElementById("fridgeitem-date").value

        const changedIngredient = {
            id: grocery.id,
            name: grocery.name,
            amount: amount,
            unit: grocery.unit,
            currentExpirationDate: grocery.currentExpirationDate,
            newExpirationDate: date
        }


        await fetch('http://localhost:8080/shopnchop/changeGrocery', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(changedIngredient)
        });

        grocery.amount = amount
        grocery.currentExpirationDate = date

        isEditable = false;


        await updateGroceries()


    }



</script>

{#if isEditable}
    <li>
        <div class="amount-wrapper">
            <input id="fridgeitem-amount" type="text" value={grocery.amount}>
            <h4>{grocery.unit}</h4>
        </div>
        <h4>{grocery.name}</h4>
        <input id="fridgeitem-date" type="text" value={grocery.currentExpirationDate}>
        <div>
            <button class="grocery-edit-button" onclick={() => {isEditable = !isEditable}}>Cancel</button>
             <button class="grocery-edit-button" onclick={confirmEdit}>Confirm</button>
        </div>
    </li>
{:else}
    <li>
        <div class="amount-wrapper">
            <h4>{grocery.amount}</h4>
            <h4>{grocery.unit}</h4>
        </div>
        <h4>{grocery.name}</h4>
        <h4>Expiration:  { grocery.currentExpirationDate}</h4>
        <div>
            <button class="grocery-edit-button" onclick={() => {isEditable = !isEditable}} >Edit</button>
            <button class="grocery-edit-button" onclick={() => deleteGrocery(grocery)}>Bin</button>
        </div>
    </li>
{/if}
