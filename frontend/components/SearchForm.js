import axios from 'axios';
import React, {useState} from 'react';

function SearchForm(props) {
    const [search, setSearch] = useState('');

    const onChangeSearch = (e) => {
        e.preventDefault();
        setSearch(e.target.value);
    };
    const onSearch = (e) => {
        e.preventDefault();
        if(search === null || search === '') {
            axios.get(common.baseURL + "") 
                .then((res) => {
                    setLists(res.data.userList)
                })
        }
    }
    return (
        <div>
            <form onSubmit={e => onSearch(e)}>
                <input type="text" value={search} placeholder="채식식당 검색" onChange={onChangeSearch} />
                <button type="submit">검색</button>
            </form>
        </div>
    );
}

export default SearchForm;