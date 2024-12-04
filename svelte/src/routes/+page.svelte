<script>
    import {onMount} from "svelte";
    import "../styles/frame.css"
    import "../styles/header.css"
    import "../styles/footer.css"
    import "../styles/main.css"
    import Header from "../components/MainHeader.svelte";
    import Footer from "../components/Footer.svelte";
    import Form from "../components/Form.svelte";
    import {goto} from "$app/navigation";


    let allRecipes;
    const days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]


    function createGroceryList() {

        goto("/fridge")



    }

    onMount(async () => {
        const response = await fetch("http://localhost:8080/shopnchop/recipes")
        allRecipes = await response.json()
    })
</script>


<div class="frame">
    <Header/>
    <div class="main-content-wrapper">
        {#each days as day}
            <Form day={day} data={allRecipes}/>
        {/each}
    </div>
    <Footer createGroceryList={() => {createGroceryList}}/>
</div>