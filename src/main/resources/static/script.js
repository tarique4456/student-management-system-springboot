const idInput = document.getElementById("id");
const nameInput = document.getElementById("name");
const emailInput = document.getElementById("email");
const courseInput = document.getElementById("course");
const searchIdInput = document.getElementById("searchId");
const tableBody = document.getElementById("studentTable");
const singleResult = document.getElementById("singleResult");
const totalStudents = document.getElementById("totalStudents");

// ADD
function addStudent() {
    if (!nameInput.value || !emailInput.value || !courseInput.value) {
        alert("Please fill all details");
        return;
    }

    fetch("/students/add", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            name: nameInput.value,
            email: emailInput.value,
            course: courseInput.value
        })
    })
    .then(() => {
        clearForm();
        loadStudents();
        alert("Student added successfully");
    });
}

// UPDATE
function updateStudent() {
    if (!idInput.value) {
        alert("ID required for update");
        return;
    }

    fetch("/students/update", {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            id: idInput.value,
            name: nameInput.value,
            email: emailInput.value,
            course: courseInput.value
        })
    })
    .then(() => {
        clearForm();
        loadStudents();
        alert("Student updated successfully");
    });
}

// LOAD ALL
function loadStudents() {
    fetch("/students/all")
        .then(res => res.json())
        .then(data => {
            tableBody.innerHTML = "";
            totalStudents.innerText = data.length;

            data.forEach(s => {
                tableBody.innerHTML += `
                    <tr>
                        <td>${s.id}</td>
                        <td>${s.name}</td>
                        <td>${s.email}</td>
                        <td>${s.course}</td>
                        <td>
                            <button class="btn-warning" onclick="deleteStudent(${s.id})">
                                Delete
                            </button>
                        </td>
                    </tr>`;
            });
        });
}

// DELETE
function deleteStudent(id) {
    if (confirm("Delete this student?")) {
        fetch("/students/delete/" + id, { method: "DELETE" })
            .then(() => loadStudents());
    }
}

// GET BY ID
function getStudentById() {
    fetch("/students/" + searchIdInput.value)
        .then(res => res.json())
        .then(s => {
            singleResult.innerText = s
                ? `Name: ${s.name}, Email: ${s.email}, Course: ${s.course}`
                : "Student not found";
        });
}

function clearForm() {
    idInput.value = "";
    nameInput.value = "";
    emailInput.value = "";
    courseInput.value = "";
}

loadStudents();
