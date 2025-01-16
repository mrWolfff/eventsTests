import React from 'react';

const Institution = ({ showInstitution, changePage }) => {


    return (
        <div className='Institution'>
            <div class="card mt-4 ms-4 shadow rounded">
                <div class="card-header bg-dark bg-gradient text-white">Instituições</div>
                <div class="card-body bg-light bg-gradient">
                    <div class="list-group">
                        <p class="list-group-item list-group-item-action" disable="true">Cadastro</p>
                        
                    </div>

                    <div class="card-footer">
                        <p type="button" class="list-group-item list-group-item-action">Instituições Existentes</p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Institution;