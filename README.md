# Pokebot
Pokemon discord bot. An attempt at remaking the now deprecated Pokecord, the original discord pokebot. (rip pokecord 2020)

Users sending messages have a random chance to spawn a pokemon that they can catch using commands. Conversation also earns them exp, which levels up their pokemon.

![wild pokemon](https://i.imgur.com/FkC0pIx.png)

## Data
Most of the data used in this bot is taken from pokeapi.co, including the sprites.
Everything under the python/ directory is output from a simple download script.
The raw json is converted into a more structured datatype by the adapters under com.pokebot.json
test/com.pokebot.types.data.ImporterTest converts and serializes all the data into json files under resources/

During runtime, raw pokemon data is kept in com.pokebot.types.data.DataRegistry. Pokemon spawning information is kept in SpawnRegistry in the same directory.

## Game Info
com.pokebot.game contains wrappers for individual pokemon and their attacks. Also has some tools for generating pokemon and interfacing with discord.

The Discord bot itself is written with the JDA library. A custom command registry is written and attached to a MessageListener.

com.pokebot.discord.EmbedUtil contains a bunch of logic for building messages.

Here is one example of an embed, which displays a user's pokemon information

![eye candy](https://i.imgur.com/fq1J83U.png)