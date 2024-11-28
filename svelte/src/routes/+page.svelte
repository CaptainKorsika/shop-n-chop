<script>
    import {onMount} from "svelte";
    onMount(() => {
        const requestHeaders = new Headers()
        requestHeaders.set("Access-Control-Allow-Origin", "*")
        requestHeaders.set('Access-Control-Allow-Headers', "*")
        fetch("http://localhost:8080/shopnchop/recipes", { headers: requestHeaders }).then((response) => {console.log(response.json())})
    })

    import "./page.css"
</script>


<div class="frame">
    <header>
        <div class="button-section">
            <div class="recipe-section">
                <button class="create-recipe">Create New Recipe</button>
                <button class="show-recipes">Show Recipes</button>
            </div>
            <div class="ingredient-section">
                <button>Add Ingredient</button>
                <button>Show Ingredients</button>
            </div>
        </div>
        <button class="show-fridge">Show Fridge</button>
    </header>
    <div class="main-content-wrapper">
        <form>
            <div class="recipe-selection-wrapper">
                <label for="monday">Monday:</label>
                <select name="monday-recipe" id="monday">
                </select>
            </div>

            <div class="recipe-buttons-wrapper">
                <button class="recipe-buttons">Edit</button>
                <button class="recipe-buttons">Cook</button>
            </div>
        </form>
        <form>
            <div class="recipe-selection-wrapper">
                <label for="tuesday">Tuesday:</label>
                <select name="tuesday-recipe" id="tuesday">
                </select>
            </div>

            <div class="recipe-buttons-wrapper">
                <button class="recipe-buttons">Edit</button>
                <button class="recipe-buttons">Cook</button>
            </div>
        </form>
        <form>
            <div class="recipe-selection-wrapper">
                <label for="wednesday">Wednesday</label>
                <select name="wednesday-recipe" id="wednesday">
                </select>
            </div>

            <div class="recipe-buttons-wrapper">
                <button class="recipe-buttons">Edit</button>
                <button class="recipe-buttons">Cook</button>
            </div>
        </form>
        <form>
            <div class="recipe-selection-wrapper">
                <label for="thursday">Thursday:</label>
                <select name="thursday-recipe" id="thursday">
                </select>
            </div>

            <div class="recipe-buttons-wrapper">
                <button class="recipe-buttons">Edit</button>
                <button class="recipe-buttons">Cook</button>
            </div>
        </form>
        <form>
            <div class="recipe-selection-wrapper">
                <label for="friday">Friday:</label>
                <select name="friday-recipe" id="friday">
                </select>
            </div>

            <div class="recipe-buttons-wrapper">
                <button class="recipe-buttons">Edit</button>
                <button class="recipe-buttons">Cook</button>
            </div>
        </form>
        <form>
            <div class="recipe-selection-wrapper">
                <label for="saturday">Saturday:</label>
                <select name="saturday-recipe" id="saturday">
                </select>
            </div>

            <div class="recipe-buttons-wrapper">
                <button class="recipe-buttons">Edit</button>
                <button class="recipe-buttons">Cook</button>
            </div>
        </form>
        <form>
            <div class="recipe-selection-wrapper">
                <label for="sunday">Sunday:</label>
                <select name="sunday-recipe" id="sunday">
                </select>
            </div>

            <div class="recipe-buttons-wrapper">
                <button class="recipe-buttons">Edit</button>
                <button class="recipe-buttons">Cook</button>
            </div>
        </form>
    </div>
    <footer>
        <button class="footer-button">Create grocery list</button>
    </footer>
</div>
