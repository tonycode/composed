Contributing
============

## Conventional Commits

based on guides:

- https://www.conventionalcommits.org/en/v1.0.0/
- https://github.com/angular/angular/blob/22b96b9/CONTRIBUTING.md#-commit-message-guidelines

```
type[(scope)]: description

body

footer
```

- types
    - feat - A new feature
    - wip - A work-in-progress change, typically a component of a larger feature which will be enabled once all tasks are complete
    - fix - A bug fix
    - redesign - Visual (UI) change that do not add a new feature nor fixes a bug
    - refactor - Any other code change that neither fixes a bug nor adds a feature
    - style - Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc)
    - docs - Documentation only changes
    - build - Changes that affect the build system or external dependencies
    - ci - Changes to our CI configuration files and scripts
- scopes
    - comida, mbank, ... - Changes related to demonstrational applications
- description
    - use the imperative, present tense: "change" not "changed" or "changes"
        - It tells someone what applying the commit will do, rather than what you did
    - include motivation for the change, and contrasts its implementation with previous behaviour
    - 80 chars or fewer
