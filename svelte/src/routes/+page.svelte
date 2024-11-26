<script>
    import {onMount} from "svelte";
    onMount(() => {
        fetch("http://localhost:8080/shopnchop/recipes").then(() => {console.log("test")})
    })
</script>



<h1>Welcome to SvelteKit</h1>
<p>Visit <a href="https://svelte.dev/docs/kit">svelte.dev/docs/kit</a> to read the documentation</p>



<header class="header">
    <button class="create-recipe">Create New Recipe</button>
    <button>Add Ingredient</button>
    <button>Show Fridge</button>
</header>
<div>
    <form>
        <label for="monday">Monday:</label>
        <select name="monday-recipe" id="monday">
<!--            List of all Recipes-->
        </select>
    </form>
    <form>
        <label for="tuesday">Tuesday:</label>
        <select name="tuesday-recipe" id="tuesday">
            <!--            List of all Recipes-->
        </select>
    </form>
    <form>
        <label for="wednesday">Wednesday:</label>
        <select name="wednesday-recipe" id="wednesday">
            <!--            List of all Recipes-->
        </select>
    </form>
    <form>
        <label for="thursday">Thursday:</label>
        <select name="thursday-recipe" id="thursday">
            <!--            List of all Recipes-->
        </select>
    </form>
    <form>
        <label for="friday">Friday:</label>
        <select name="friday-recipe" id="friday">
            <!--            List of all Recipes-->
        </select>
    </form>
    <form>
        <label for="saturday">Saturday:</label>
        <select name="saturday-recipe" id="saturday">
            <!--            List of all Recipes-->
        </select>
    </form>
    <form>
        <label for="sunday">Sunday:</label>
        <select name="sunday-recipe" id="sunday">
            <!--            List of all Recipes-->
        </select>
    </form>
</div>
<footer>
    <button>Create grocery list</button>
</footer>