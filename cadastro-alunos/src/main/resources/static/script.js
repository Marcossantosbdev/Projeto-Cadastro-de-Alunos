const apiUrl = '/alunos';

const form = document.getElementById('form-aluno');
const tabelaBody = document.getElementById('tabela-alunos');
const alunoIdInput = document.getElementById('aluno-id');
const nomeInput = document.getElementById('nome');
const matriculaInput = document.getElementById('matricula');
const emailInput = document.getElementById('email');
const cursoInput = document.getElementById('curso');
const telefoneInput = document.getElementById('telefone');
const enderecoInput = document.getElementById('endereco');

const cancelarBtn = document.getElementById('cancelar');

// Máscara de telefone
telefoneInput.addEventListener('keyup', function(e) {
    let valor = e.target.value.replace(/\D/g, '');
    valor = valor.substring(0, 11); 
    let formatado = '';
    if (valor.length > 0) {
        formatado = '(' + valor.substring(0,2);
    }
    if (valor.length > 2) {
        formatado += ') ' + valor.substring(2,7);
    }
    if (valor.length > 7) {
        formatado += '-' + valor.substring(7,11);
    }
    e.target.value = formatado;
});

document.addEventListener('DOMContentLoaded', carregarAlunos);

form.addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = alunoIdInput.value;
    const aluno = {
        nomeAluno: nomeInput.value,
        matriculaAluno: matriculaInput.value,
        emailAluno: emailInput.value,
        cursoAluno: cursoInput.value,
        telefoneAluno: telefoneInput.value.replace(/\D/g, ''),
        enderecoAluno: enderecoInput.value
    };

    try {
        let response;
        if (id) {
            response = await fetch(`${apiUrl}/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ id: id, ...aluno })
            });
        } else {
            response = await fetch(apiUrl, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(aluno)
            });
        }

        if (response.ok) {
            alert(id ? "Aluno atualizado com sucesso!" : "Aluno cadastrado com sucesso!");
            limparFormulario();
            carregarAlunos();
        } else {
            const erroData = await response.json();

            if (Array.isArray(erroData)) {
                let mensagens = erroData.map(err => `${err.mensagem}`).join('\n');
                alert("Erros de validação:\n" + mensagens);
            } else if (erroData.mensagem) {
                alert("Erro do Servidor: " + erroData.mensagem);
            } else {
                alert("Erro na API:\n" + JSON.stringify(erroData));
            }
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
        alert("Erro ao conectar com o servidor.");
    }
});

async function carregarAlunos() {
    try {
        const response = await fetch(apiUrl);
        const alunos = await response.json();
        tabelaBody.innerHTML = '';

        alunos.forEach(aluno => {
            const row = document.createElement('tr');

            // ATENÇÃO AQUI: Verifique se os nomes batem com os atributos da sua Entidade Aluno.java
            row.innerHTML = `
                <td>${aluno.nomeAluno || aluno.nome}</td>
                <td>${aluno.matriculaAluno || aluno.matricula}</td>
                <td>${aluno.emailAluno || aluno.email}</td>
                <td>${aluno.cursoAluno || aluno.curso}</td>
                <td>${aluno.telefoneAluno || aluno.telefone || ''}</td>
                <td>${aluno.enderecoAluno || aluno.endereco || ''}</td>
                <td>
                    <button class="editar" onclick="editarAluno(${aluno.id})">Editar</button>
                    <button class="excluir" onclick="excluirAluno(${aluno.id})">Excluir</button>
                </td>
            `;
            tabelaBody.appendChild(row);
        });
    } catch (error) {
        console.error('Erro ao carregar alunos:', error);
    }
}

async function editarAluno(id) {
    try {
        const response = await fetch(`${apiUrl}/${id}`);
        const aluno = await response.json();
        alunoIdInput.value = aluno.id;

        // Se houver nomeAluno usa ele, senão usa nome
        nomeInput.value = aluno.nomeAluno || aluno.nome;
        matriculaInput.value = aluno.matriculaAluno || aluno.matricula;
        emailInput.value = aluno.emailAluno || aluno.email;

        const cursoSelect = document.getElementById('curso');
        const cursoAtual = aluno.cursoAluno || aluno.curso;
        for (let option of cursoSelect.options) {
            if (option.value === cursoAtual) {
                option.selected = true;
                break;
            }
        }

        telefoneInput.value = aluno.telefoneAluno || aluno.telefone || '';
        enderecoInput.value = aluno.enderecoAluno || aluno.endereco || '';
        cancelarBtn.style.display = 'inline-block';
    } catch (error) {
        console.error('Erro ao editar aluno:', error);
    }
}

async function excluirAluno(id) {
    if (confirm('Tem certeza que deseja excluir este aluno?')) {
        try {
            await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
            carregarAlunos();
        } catch (error) {
            console.error('Erro ao excluir aluno:', error);
        }
    }
}

function limparFormulario() {
    form.reset();
    alunoIdInput.value = '';
    cancelarBtn.style.display = 'none';
}