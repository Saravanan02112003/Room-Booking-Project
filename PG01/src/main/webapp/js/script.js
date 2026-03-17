// Simple Toggle Menu Function for Mobile Navigation
function toggleMenu() {
    const navLinks = document.querySelector('.nav-links');
    navLinks.classList.toggle('active');
}

// Optionally, you can add an event listener if needed.
document.querySelector('.menu-icon').addEventListener('click', toggleMenu);


function toggleMenu() {
    const menu = document.querySelector('.nav-links');
    menu.classList.toggle('active');
    
    const menuIcon = document.querySelector('.menu-icon');
    menuIcon.classList.toggle('active');
}

