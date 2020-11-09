function sha512(str) {
    let md = forge.md.sha512.create().update(str);
    return md.digest().toHex()
}