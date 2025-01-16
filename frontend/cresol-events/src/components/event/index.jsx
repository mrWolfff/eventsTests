import React, { useState, useEffect } from "react";


const Event = ({ showInstitution, changePage }) => {
    const [events, setEvents] = useState([]);
    const executeOnLoad = () => {
        console.log('Tela aberta, função executada!');
      };
    useEffect(() => {
        executeOnLoad();
      }, []);
    const handleDelete = (index) => {
        fetch('http://localhost/event/' + index, {
            method: 'POST',
            body: index,
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    console.log("Erro: " + response);
                }
            })
            .then(data => {
                console.log("then");
            })
            .catch(error => {
                alert("Erro " + error);
            });
    }
    const handleSubmit = (event) => {
        event.preventDefault();
        let data = event.target[0].value;
        const formData = new FormData();
        formData.append(data);

        fetch('http://localhost/event', {
            method: 'POST',
            body: formData,
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    console.log("Erro: " + response);
                }
            })
            .then(data => {
                console.log("then");
                setEvents(data);
            })
            .catch(error => {
                alert("Erro " + error);
            });
    };

    return (
        <div className='Event'>
            <div class="card mt-4 ms-4 shadow rounded">
                <div class="card-header bg-dark bg-gradient text-white">Eventos</div>
                <div class="card-body bg-light bg-gradient">
                    <form onSubmit={handleSubmit}>
                        <p type="button" class="list-group-item list-group-item-action disable">Cadastro</p>
                        <div className="input-group mb-3 w-75 ">
                            <span className="input-group-text bg-dark-subtle"></span>
                            <input type="text" className="form-control pb-1" name="name" placeholder="Digite o nome do evento" />
                        </div>
                        <div className="row mb-3">
                            <div className="col-6">
                                <div className="form-group w-75">
                                    <label for="dateInput" class="form-label">Selecione uma data de inicio</label>
                                    <input type="date" className="form-control pb-1" name="initialDate" />
                                </div>
                            </div>
                            <div className="col-6">
                                <div className="form-group w-75">
                                    <label for="dateInput" class="form-label">Selecione uma data final</label>
                                    <input type="date" className="form-control pb-1" name="finalDate" />
                                </div>
                            </div>
                        </div>
                        <div className="input-group mb-3">
                            <button type="submit" class="btn btn-primary">Cadastrar</button>
                        </div>
                    </form>
                    <div class="card-footer">
                        <p type="button" class="list-group-item list-group-item-action disable">Eventos Existentes</p>
                        <div className="events">
                            <div className="row row-cols-5">
                                {events.map((item, index) => (
                                    <div className="col-3" key={index}>
                                        <div className="card mt-1 me-1">
                                            <div className="card-header"></div>
                                            <div className="card-body container">
                                                {item}
                                            </div>
                                            <div className="card-footer">
                                                <button className="btn btn-danger" onClick={() => handleDelete(index)}>Excluir</button>
                                            </div>
                                        </div>
                                    </div>
                                ))}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Event;