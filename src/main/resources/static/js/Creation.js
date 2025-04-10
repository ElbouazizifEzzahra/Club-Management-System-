// Gestion de l'affichage des sections
function showSection(sectionId, event) {
    event.preventDefault(); // Empêche la soumission du formulaire

    // Masquer toutes les sections
    document.querySelectorAll('.section').forEach(section => {
        section.classList.remove('active');
    });

    // Afficher la section sélectionnée
    document.getElementById(sectionId).classList.add('active');

    // Mettre à jour les boutons de navigation
    document.querySelectorAll('.nav-button').forEach(button => {
        button.classList.remove('active');
    });
    event.target.classList.add('active');
}


// Gestion des membres
let members = [];

function addMemberField() {
    const memberId = Date.now();
    const memberDiv = document.createElement('div');
    memberDiv.className = 'member-item';
    memberDiv.innerHTML = `
        <input type="email" name="members" placeholder="Email du membre">
        <button type="button" class="remove-member-btn" onclick="removeMember(this)">×</button>
    `;
    document.getElementById('membersList').appendChild(memberDiv);
}

function removeMember(button) {
    button.parentElement.remove();
}




function updateMember(id, value) {
    const member = members.find(m => m.id === id);
    if (member) {
        member.email = value;
        event.target.nextElementSibling.value = value; // Met à jour le champ caché
    }
}



// Validation et création du JSON
function submitClub() {
    const clubData = {
        name: document.getElementById('clubName').value,
        description: document.getElementById('clubDescription').value,
        domain: document.getElementById('clubDomain').value,
        members: members.filter(m => m.email !== '')
    };

    // Afficher le récapitulatif
    document.getElementById('summaryContent').innerHTML = `
        <p><strong>Nom du club:</strong> ${clubData.name}</p>
        <p><strong>Description:</strong> ${clubData.description}</p>
        <p><strong>Domaine:</strong> ${clubData.domain}</p>
        <p><strong>Nombre de membres:</strong> ${clubData.members.length}</p>
    `;

    // Soumettre le formulaire
    document.querySelector('form').submit();
}