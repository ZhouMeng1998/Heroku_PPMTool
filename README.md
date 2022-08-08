# Full Stack Personal Project Management Tool: Spring Boot 2.0, ReactJS, Redux

This project was bootstrapped with [Create React App](https://github.com/facebookincubator/create-react-app).

It provides a 'Personal Project Management Tool', which allows the user to add a project list, add comments regarding each project, and assign project tasks to each project - setting priority and status of those project tasks. each user can only access his own projects.

![请选择超清](https://raw.githubusercontent.com/ZhouMeng1998/IMG/main/Snipaste_2022-08-08_17-16-45.jpg)

## Getting started

This APP is deployed on heroku. You can access it on  [Personal Project Management Tool](https://personalprojectmanagementtools.herokuapp.com/)

**Default Username and Password:**

- `username:cloud@cloud.com`	
- `password:123456`

> `You can also register your USER via register page,choose:
>     -fullName
>     -email(UserName)
>     -password
>     -confirmPassword
> Then Login with that USER.
>
> then create your Project or Project List,
> add Project Tasks.
> then manageing your Projects progress will become way Easier. 


## Technology Stack

### Architecture

The project is based on **front-end and back-end separation** Architecture. 

### Technologies:

- Build **RESTful APIs** with **Spring boot** for CRUD operations
- Persist data with **Hibernate** and **Spring Data**
- Create front end using **ReactJS** and **Boostrap**
- Use **Redux** and **Thunk** to manage the state of our application in the front-end
- Secure the application using **Spring Security**

## Folder Structure

After creation, your project should look like this:

```
my-app/
  README.md
  node_modules/
  package.json
  public/
    index.html
    favicon.ico
  src/
    App.css
    App.js
    App.test.js
    index.css
    index.js
    logo.svg
```

For the project to build, **these files must exist with exact filenames**:

- `public/index.html` is the page template;
- `src/index.js` is the JavaScript entry point.

You can delete or rename the other files.

You may create subdirectories inside `src`. For faster rebuilds, only files inside `src` are processed by Webpack.
You need to **put any JS and CSS files inside `src`**, otherwise Webpack won’t see them.

Only files inside `public` can be used from `public/index.html`.
Read instructions below for using assets from JavaScript and HTML.

You can, however, create more top-level directories.
They will not be included in the production build so you can use them for things like documentation.

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.
Open [http://localhost:3000](http://localhost:3000/) to view it in the browser.

The page will reload if you make edits.
You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.
See the section about [running tests](https://github.com/AgileIntelligence/AgileIntPPMTool/tree/master/ppmtool-react-client#running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.
Your app is ready to be deployed!

See the section about [deployment](https://github.com/AgileIntelligence/AgileIntPPMTool/tree/master/ppmtool-react-client#deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can’t go back!**

If you aren’t satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (Webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you’re on your own.

You don’t have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn’t feel obligated to use this feature. However we understand that this tool wouldn’t be useful if you couldn’t customize it when you are ready for it.

## Contributing

Meng Zhou