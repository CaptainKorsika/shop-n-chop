<script>
    import "../styles/footer.css"
    import {goto} from "$app/navigation";

    function navigate() {
        goto("/groceries")
    }




</script>


<footer>
    <button class="footer-button" onclick={navigate}>Create grocery list</button>
</footer>