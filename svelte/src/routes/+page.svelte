<script>
    import {onMount} from "svelte";
    import "../styles/main-frame.css"
    import Header from "../components/MainHeader.svelte";
    import Footer from "../components/Footer.svelte";
    import Form from "../components/Form.svelte";


    let allRecipes;
    const days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]


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
    <Footer/>
</div>