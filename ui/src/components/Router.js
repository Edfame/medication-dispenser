import React, {Component} from "react";
import {BrowserRouter, Route, Switch} from "react-router-dom";
import Home from "./Home/Home";
import Administrations from "./Administrations/Administrations";


class Router extends Component {

    render() {
        return (
            <BrowserRouter>
                <Switch>
                    {<Route exact path='/' component={Home}/>}

                    {<Route exact path="/administrations" component={Administrations}/>}
                    {/*<Route path="/druglist" component={DrugList}/>*/}

                    {/*<Route path="/login" component={Login}/>*/}
                    {/*<Route path="/register" component={Register}/>*/}
                    {/*<PrivateRoute path="/logout" component={Logout} isAuthenticated={isAuthenticated}/>*/}

                    {/*<PrivateRoute path="/drugs" component={MyDrugs} isAuthenticated={isAuthenticated}/>*/}
                    {/*<PrivateRoute path="/user" component={User} isAuthenticated={isAuthenticated}/>*/}

                    {/*<Route path="/*" component={Error_404}/>*/}
                </Switch>
            </BrowserRouter>
        );
    }

}

export default Router