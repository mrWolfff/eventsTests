import React from 'react';

const selectionBar = ({ showInstitution, changePage }) => {

    return (
        <div className='selectionBar'>
            <div class="card mt-4 ms-4 shadow rounded">
                <div class="card-header bg-dark bg-gradient text-white">Opções</div>
                <div class="card-body bg-light bg-gradient">
                    <div class="list-group">
                        <button type="button" onClick={changePage} disabled={!showInstitution} class="list-group-item list-group-item-action">Eventos</button>
                        <button type="button" onClick={changePage} disabled={showInstitution} class="list-group-item list-group-item-action">Instituições</button>
                    </div>

                    <div class="card-footer">
                    </div>
                </div>
            </div>
        </div>
    );
};

export default selectionBar;