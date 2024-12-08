<script>
    import "../../styles/frame.css"
    import "../../styles/header.css"
    import "../../styles/footer.css"
    import "../../styles/addIngredients.css"

    import {goto} from "$app/navigation";

    async function confirmInput() {

        const name = document.getElementById("name-input").value
        const unit = document.getElementById("unit-input").value
        const category = document.getElementById("category-input").value
        const durability = document.getElementById("durability-input").value

        const ingredientDTO = {
            name: name,
            unit: unit,
            category: category,
            durability: durability
        }

        await fetch(`http://localhost:8080/shopnchop/ingredient/${name}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(ingredientDTO)
        });

        navigate("ingredients")

    }

    function navigate(name = "/") {
        goto(`${name}`);
    }
</script>

<div class="frame">
    <header class="add-ingredient-header">
        <button onclick={() => {navigate("ingredients")}}>Cancel</button>
    </header>
    <div class="main-content-wrapper">
        <form>
            <label>Name:</label>
            <input type="text" id="name-input">
        </form>
        <form>
            <label>Unit:</label>
            <input type="text" id="unit-input">
        </form>
        <form>
            <label>Category:</label>
            <input type="text" id="category-input">
        </form>
        <form>
            <label>Durability:</label>
            <input type="text" id="durability-input">
        </form>
    </div>
    <footer>
        <button onclick={confirmInput}>Confirm</button>
    </footer>
</div>